import {useState} from "react";
import {CompletedCurrencyExchanges} from "../component/CompletedCurrencyExchanges";
import {CurrencyConversionForm} from "../component/CurrencyConversionForm";
import {ExchangeRateDto} from "../api/generated/bpmn-engine";
import {UserDto} from "../api/generated/account-service";
import {OtpSolvingForm} from "../component/OtpSolvingForm";
import {TransactionHandlingSettingForm} from "../component/TransactionHandlingSettingForm";
import {ExchangeRatesWindow} from "../component/ExchangeRatesWindow";
import {UserSelectorWindow} from "../component/UserSelectorWindow";

export default function Home() {
    const [selectedUser, setSelectedUser] = useState<UserDto | null>(null);

    const [reloadTrigger, setReloadTrigger] = useState(0);

    const [exchangeRates, setExchangeRates] = useState<ExchangeRateDto[] | null>(null);

    return (
        <div className="min-h-screen bg-gray-100 px-4 py-8 flex items-center justify-center">
            <div className="w-full max-w-7xl flex flex-col md:flex-row items-start justify-center gap-6">
                <div className="flex flex-col gap-6">
                    <ExchangeRatesWindow exchangeRates={exchangeRates} setExchangeRates={setExchangeRates}/>

                    <OtpSolvingForm selectedUser={selectedUser}/>

                    <TransactionHandlingSettingForm/>
                </div>

                <div className="flex flex-col gap-6 flex-1">
                    <UserSelectorWindow selectedUser={selectedUser} setSelectedUser={setSelectedUser}
                                        reloadTrigger={reloadTrigger}/>
                    <CurrencyConversionForm selectedUser={selectedUser}
                                            onExchangeCreated={() => setReloadTrigger(prev => prev + 1)}
                                            exchangeRates={exchangeRates}/>
                </div>

                <CompletedCurrencyExchanges reloadTrigger={reloadTrigger}/>
            </div>
        </div>
    );
}
