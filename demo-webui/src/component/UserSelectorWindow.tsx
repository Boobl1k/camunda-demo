import React, {useEffect, useState} from "react";
import {UserControllerService, UserDto} from "../api/generated/account-service";

export const UserSelectorWindow = ({
                                       selectedUser,
                                       setSelectedUser,
                                       reloadTrigger
                                   }: {
    selectedUser: UserDto | null;
    setSelectedUser: (user: UserDto | null) => void;
    reloadTrigger: any;
}) => {
    const [users, setUsers] = useState<UserDto[]>([]);

    useEffect(() => {
        UserControllerService.getUsers()
            .then(users => {
                setUsers(users)
                if (selectedUser) {
                    setSelectedUser(users.find(u => u.id === selectedUser.id)!)
                }
            })
            .catch(err => {
                console.error("Ошибка загрузки пользователей", err);
            });
    }, [reloadTrigger]);

    return (
        <div className="bg-white rounded-2xl shadow p-4 space-y-2 w-full">
            <label className="block text-sm font-medium mb-2">Пользователи</label>

            <div className="flex overflow-x-auto gap-4">
                {users.map(user => {
                    const isSelected = selectedUser?.id === user.id;

                    return (
                        <div
                            key={user.id}
                            onClick={() => setSelectedUser(user)}
                            className={`cursor-pointer border rounded-xl p-4 min-w-[200px] flex-shrink-0 transition-all
                                ${isSelected
                                ? 'border-blue-600 bg-blue-50 shadow'
                                : 'border-gray-300 hover:border-blue-400'}
                            `}
                        >
                            <div className="font-semibold text-lg">{user.name}</div>
                            <ul className="mt-2 text-sm text-gray-700 space-y-1">
                                {user.accounts.map(acc => (
                                    <li key={acc.id}>
                                        {acc.currency}: {(acc.amount / 100).toFixed(2)}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};
