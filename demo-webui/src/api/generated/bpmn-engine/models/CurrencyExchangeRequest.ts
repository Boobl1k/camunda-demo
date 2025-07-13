/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { CurrencyExchangeSource } from './CurrencyExchangeSource';
import type { CurrencyExchangeTarget } from './CurrencyExchangeTarget';
export type CurrencyExchangeRequest = {
    userId: string;
    source: CurrencyExchangeSource;
    target: CurrencyExchangeTarget;
    exchangeRate: number;
};

