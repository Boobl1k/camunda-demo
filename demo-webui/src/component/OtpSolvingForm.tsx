import {UserDto} from "../api/generated/account-service";
import {useState} from "react";
import {OtpMockControllerService} from "../api/generated/bpmn-engine";

export const OtpSolvingForm = ({selectedUser}: { selectedUser: UserDto | null }) => {
    const [otpPassword, setOtpPassword] = useState(0);
    const [otpResult, setOtpResult] = useState<{ solved: boolean; otpId: string } | null>(null);

    const handleOtpSolve = async () => {
        if (!selectedUser) return
        try {
            const result = await OtpMockControllerService.solveOtp({
                userId: selectedUser.id,
                password: otpPassword,
            });
            setOtpResult({solved: result.solved, otpId: result.otpId});
        } catch (err) {
            console.error(err);
            setOtpResult(null);
        }
    };

    return (
        <div className="bg-white rounded-2xl shadow p-4 w-fit self-center flex-shrink-0">
            <h2 className="text-lg font-semibold mb-2">Подтверждение OTP</h2>
            <div className="space-y-3">
                <div>
                    <label className="block text-sm font-medium">Password</label>
                    <input
                        type="number"
                        value={otpPassword}
                        onChange={(e) => setOtpPassword(Number(e.target.value))}
                        className="mt-1 px-3 py-2 border rounded-md w-64"
                    />
                </div>

                <button
                    onClick={handleOtpSolve}
                    className="w-full bg-green-600 text-white py-2 rounded-md hover:bg-green-700 transition"
                >
                    Подтвердить
                </button>

                {otpResult && (
                    <div
                        className={`mt-4 px-4 py-2 rounded-md text-sm font-medium flex items-center gap-2 ${
                            otpResult.solved
                                ? "bg-green-50 text-green-700 border border-green-300"
                                : "bg-red-50 text-red-700 border border-red-300"
                        }`}
                    >
                        {otpResult.solved ? "✅ Успешно подтвержден OTP" : "❌ OTP не подтвержден"}
                    </div>
                )}
            </div>
        </div>
    );
}