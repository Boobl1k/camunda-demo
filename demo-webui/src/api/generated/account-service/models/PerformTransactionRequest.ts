/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export type PerformTransactionRequest = {
    currency: string;
    transactionType: PerformTransactionRequest.transactionType;
    amount: number;
};
export namespace PerformTransactionRequest {
    export enum transactionType {
        WITHDRAW = 'WITHDRAW',
        DEPOSIT = 'DEPOSIT',
    }
}

