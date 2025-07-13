import React, {useEffect, useState} from "react";
import {CurrencyExchangeControllerService, CurrencyExchangeDto, ExchangeRateDto} from "../api/generated/bpmn-engine";
import {UserDto} from "../api/generated/account-service";

export const CurrencyConversionForm = ({
                                           selectedUser,
                                           onExchangeCreated,
                                           exchangeRates
                                       }: {
    selectedUser: UserDto | null,
    onExchangeCreated: () => void,
    exchangeRates: ExchangeRateDto[] | null
}) => {
    const [availableCurrencies, setAvailableCurrencies] = useState<string[]>([]);

    const [sourceAccountId, setSourceAccountId] = useState('');
    const [sourceAmount, setSourceAmount] = useState(500000);
    const [sourceCurrency, setSourceCurrency] = useState('');

    const [targetAccountId, setTargetAccountId] = useState('');
    const [targetCurrency, setTargetCurrency] = useState('');

    const [exchangeRate, setExchangeRate] = useState(0.012);
    const [currencyExchangeId, setCurrencyExchangeId] = useState<string | null>(null);
    const [currencyExchange, setCurrencyExchange] = useState<CurrencyExchangeDto | null>(null);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (!exchangeRates) return;

        const foundRate = exchangeRates.find(
            rate =>
                rate.sourceCurrency === sourceCurrency &&
                rate.targetCurrency === targetCurrency
        );

        if (foundRate) {
            setExchangeRate(foundRate.exchangeRate);
        } else {
            setExchangeRate(0)
        }
    }, [sourceCurrency, targetCurrency, exchangeRates]);

    useEffect(() => {
        if (selectedUser) {
            const accountIds = selectedUser.accounts.map(a => a.id);
            const currencies = [...new Set(selectedUser.accounts.map(acc => acc.currency))];
            setAvailableCurrencies(currencies);

            const firstAcc = selectedUser.accounts[0];
            if (firstAcc) {
                if (!accountIds.find(a => a === sourceAccountId)) {
                    setSourceAccountId(firstAcc.id);
                    setSourceCurrency(firstAcc.currency);
                }
                if (!accountIds.find(a => a === targetAccountId)) {
                    setTargetAccountId(firstAcc.id);
                    setTargetCurrency(firstAcc.currency);
                }
            }
        }
    }, [selectedUser]);

    const handleSourceCurrencyChange = (currency: string) => {
        if (currency) {
            setSourceCurrency(currency);
            const acc = selectedUser?.accounts.find(a => a.currency === currency);
            if (acc) setSourceAccountId(acc.id);
        }
    };

    const handleTargetCurrencyChange = (currency: string) => {
        if (currency) {
            setTargetCurrency(currency);
            const acc = selectedUser?.accounts.find(a => a.currency === currency);
            if (acc) setTargetAccountId(acc.id);
        }
    };

    useEffect(() => {
        if (!currencyExchangeId) return;

        const interval = setInterval(async () => {
            try {
                const result = await CurrencyExchangeControllerService.getCurrencyExchange(currencyExchangeId);
                setCurrencyExchange(result);

                if (
                    result.status === CurrencyExchangeDto.status.SUCCESSFUL ||
                    result.status === CurrencyExchangeDto.status.FAILED
                ) {
                    onExchangeCreated();
                    clearInterval(interval);
                }
            } catch (err) {
                console.error("Ошибка при получении транзакции:", err);
                clearInterval(interval);
            }
        }, 1000);

        return () => clearInterval(interval);
    }, [currencyExchangeId]);

    const handleConvert = async () => {
        if (!selectedUser) return;

        setLoading(true);
        setError(null);
        setCurrencyExchangeId(null);
        setCurrencyExchange(null);

        try {
            const response = await CurrencyExchangeControllerService.processCurrencyExchangeRequest({
                userId: selectedUser.id,
                exchangeRate,
                source: {
                    accountId: sourceAccountId,
                    amount: sourceAmount,
                    currency: sourceCurrency,
                },
                target: {
                    accountId: targetAccountId,
                    currency: targetCurrency,
                },
            });

            setCurrencyExchangeId(response.currencyExchangeId);
            onExchangeCreated();
        } catch (err) {
            console.error(err);
            setError("Ошибка при выполнении запроса");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="bg-white rounded-2xl shadow p-6 space-y-4 flex-1">
            <h1 className="text-2xl font-semibold">Демо конвертации валют</h1>

            <div>
                <label className="block text-sm font-medium">Source Amount</label>
                <input
                    type="number"
                    value={sourceAmount / 100}
                    onChange={(e) => setSourceAmount(Number(e.target.value) * 100)}
                    className="mt-1 w-full px-3 py-2 border rounded-md"
                    min={1}
                    max={1000000000}
                />
            </div>

            <div>
                <label className="block text-sm font-medium">Source Currency</label>
                <select
                    value={sourceCurrency}
                    onChange={(e) => handleSourceCurrencyChange(e.target.value)}
                    className="mt-1 w-full px-3 py-2 border rounded-md bg-white"
                >
                    {availableCurrencies.map(currency => (
                        <option key={currency} value={currency}>{currency}</option>
                    ))}
                </select>
            </div>

            <div>
                <label className="block text-sm font-medium">Target Currency</label>
                <select
                    value={targetCurrency}
                    onChange={(e) => handleTargetCurrencyChange(e.target.value)}
                    className="mt-1 w-full px-3 py-2 border rounded-md bg-white"
                >
                    {availableCurrencies.map(currency => (
                        <option key={currency} value={currency}>{currency}</option>
                    ))}
                </select>
            </div>

            <div>
                <label className="block text-sm font-medium">Exchange Rate</label>
                <input
                    type="number"
                    step="0.0001"
                    value={exchangeRate}
                    onChange={(e) => setExchangeRate(Number(e.target.value))}
                    className="mt-1 w-full px-3 py-2 border rounded-md"
                />
            </div>

            <button
                onClick={handleConvert}
                disabled={loading}
                className="w-full px-6 py-3 bg-blue-600 text-white rounded-xl hover:bg-blue-700 disabled:opacity-50 transition"
            >
                {loading ? "Обработка..." : "Выполнить конвертацию"}
            </button>

            {currencyExchangeId && (
                <div className="text-green-600 text-sm mt-2 break-all">
                    ✅ Конвертация валют запущена: <span className="font-mono">{currencyExchangeId}</span>
                </div>
            )}

            {error && (
                <div className="text-red-600 text-sm mt-2">
                    ⚠️ {error}
                </div>
            )}

            {currencyExchange && (
                <div
                    className={`mt-4 px-4 py-2 rounded-md text-sm font-medium flex items-center gap-2 ${
                        currencyExchange.status === CurrencyExchangeDto.status.SUCCESSFUL
                            ? 'bg-green-50 text-green-700 border border-green-300'
                            : currencyExchange.status === CurrencyExchangeDto.status.FAILED
                                ? 'bg-red-50 text-red-700 border border-red-300'
                                : 'bg-yellow-50 text-yellow-700 border border-yellow-300'
                    }`}
                >
                    {currencyExchange.status === CurrencyExchangeDto.status.SUCCESSFUL
                        ? '✅ Транзакция успешно завершена'
                        : currencyExchange.status === CurrencyExchangeDto.status.FAILED
                            ? `❌ Конвертация завершилась с ошибкой: ${currencyExchange.errorReason}`
                            : '⏳ Конвертация обрабатывается...'}
                </div>
            )}
        </div>
    );
};
