import {ExchangeRateControllerService, ExchangeRateDto} from "../api/generated/bpmn-engine";
import {useEffect, useState} from "react";

export const ExchangeRatesWindow = ({exchangeRates, setExchangeRates}: {
    exchangeRates: ExchangeRateDto[] | null,
    setExchangeRates: (exchangeRates: ExchangeRateDto[] | null) => void
}) => {

    const [isRatesLoading, setIsRatesLoading] = useState(false);
    const [ratesError, setRatesError] = useState<string | null>(null);

    useEffect(() => {
        const fetchExchangeRates = async () => {
            setIsRatesLoading(true);
            setRatesError(null);
            try {
                const rates = await ExchangeRateControllerService.getExchangeRates();
                setExchangeRates(rates);
            } catch (e) {
                console.error(e);
                setRatesError("Не удалось загрузить курсы валют");
            } finally {
                setIsRatesLoading(false);
            }
        };

        fetchExchangeRates();
    }, []);

    return (
        <div className="bg-white rounded-2xl shadow p-4 w-fit self-center flex-shrink-0">
            <h2 className="text-lg font-semibold mb-2">Курсы валют</h2>

            {isRatesLoading && <div className="text-sm text-gray-500">Загрузка...</div>}
            {ratesError && <div className="text-sm text-red-600">{ratesError}</div>}

            {!isRatesLoading && !ratesError && exchangeRates && (
                <ul className="space-y-1 text-sm text-gray-700">
                    {exchangeRates.map((rate, index) => (
                        <li key={index} className="flex justify-between gap-4">
                    <span>
                        {rate.sourceCurrency} → {rate.targetCurrency}
                    </span>
                            <span className="font-mono">{rate.exchangeRate?.toFixed(4)}</span>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}