/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { CurrencyExchangeRequest } from './CurrencyExchangeRequest';
import type { TransactionCalculationResponse } from './TransactionCalculationResponse';
export type CurrencyExchangeDto = {
    id: string;
    status: CurrencyExchangeDto.status;
    errorReason?: string;
    request: CurrencyExchangeRequest;
    transactionCalculationResponse?: TransactionCalculationResponse;
};
export namespace CurrencyExchangeDto {
    export enum status {
        PENDING = 'PENDING',
        SUCCESSFUL = 'SUCCESSFUL',
        FAILED = 'FAILED',
    }
}

