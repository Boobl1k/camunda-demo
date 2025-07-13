import {useEffect, useState} from 'react';
import {CurrencyExchangeControllerService, CurrencyExchangeDto} from '../api/generated/bpmn-engine';

export const CompletedCurrencyExchanges = ({reloadTrigger}: { reloadTrigger: any }) => {
    const [exchanges, setExchanges] = useState<CurrencyExchangeDto[]>([]);

    const loadExchanges = async () => {
        try {
            setExchanges(await CurrencyExchangeControllerService.getCurrencyExchanges());
        } catch (error) {
            console.error('Ошибка при загрузке запросов на конвертацию:', error);
        }
    };

    useEffect(() => {
        loadExchanges();
    }, [reloadTrigger]);

    return (
        <div className="bg-white rounded-2xl shadow p-4 max-w-sm w-full flex-shrink">
            <h2 className="text-lg font-bold mb-2">История</h2>
            {exchanges.length === 0 ? (
                <p className="text-gray-500">Пока ничего нет</p>
            ) : (
                <ul className="space-y-2">
                    {exchanges.map((ex) => (
                        <li key={ex.id} className="p-2 border rounded">
                            <div>Статус: <strong>{ex.status}</strong></div>
                            {ex.request && (
                                <div>Из: {(ex.request.source.amount / 100).toFixed(2)} {ex.request.source.currency}</div>
                            )}
                            {ex.transactionCalculationResponse && (
                                <div>В: {(ex.transactionCalculationResponse.target.amount / 100).toFixed(2)} {ex.transactionCalculationResponse.target.currency}</div>
                            )}
                            {ex.status === 'FAILED' && (
                                <div className="text-red-500">Причина ошибки: {ex.errorReason}</div>
                            )}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};
