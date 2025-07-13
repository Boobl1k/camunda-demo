/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export type PerformTransactionResponse = {
    currency: string;
    transactionType: PerformTransactionResponse.transactionType;
    amount: number;
    currentAmount: number;
};
export namespace PerformTransactionResponse {
    export enum transactionType {
        WITHDRAW = 'WITHDRAW',
        DEPOSIT = 'DEPOSIT',
    }
}

