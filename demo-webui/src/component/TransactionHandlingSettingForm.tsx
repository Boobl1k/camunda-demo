import {useState} from "react";
import {SettingsControllerService} from "../api/generated/account-service";

export const TransactionHandlingSettingForm = () => {

    const [transactionHandlingSetting, setTransactionHandlingSetting] = useState<"HANDLE_SUCCESSFULLY" | "HANDLE_NEXT_DEPOSIT_WITH_FAILURE">("HANDLE_SUCCESSFULLY");
    const [settingResult, setSettingResult] = useState<string | null>(null);


    const handleChangeTransactionHandling = async () => {
        try {
            await SettingsControllerService.changeTransactionHandlingSetting(transactionHandlingSetting);
            setSettingResult("✅ Успешно применено");
        } catch (error) {
            console.error(error);
            setSettingResult("❌ Ошибка при применении");
        }
    };

    return (
        <div className="bg-white rounded-2xl shadow p-4 w-fit self-center flex-shrink-0">
            <h2 className="text-lg font-semibold mb-2">Настройка обработки транзакций</h2>
            <div className="space-y-3">
                <div>
                    <label className="block text-sm font-medium">Режим обработки</label>
                    <select
                        value={transactionHandlingSetting}
                        onChange={(e) => setTransactionHandlingSetting(e.target.value as ("HANDLE_SUCCESSFULLY" | "HANDLE_NEXT_DEPOSIT_WITH_FAILURE"))}
                        className="mt-1 px-3 py-2 border rounded-md w-64"
                    >
                        <option value="HANDLE_SUCCESSFULLY">Обработать успешно</option>
                        <option value="HANDLE_NEXT_DEPOSIT_WITH_FAILURE">Отменить следующую</option>
                    </select>
                </div>

                <button
                    onClick={handleChangeTransactionHandling}
                    className="w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700 transition"
                >
                    Применить настройку
                </button>

                {settingResult && (
                    <div
                        className={`mt-2 text-sm font-medium ${
                            settingResult.startsWith("✅")
                                ? "text-green-700"
                                : "text-red-700"
                        }`}
                    >
                        {settingResult}
                    </div>
                )}
            </div>
        </div>
    );
}