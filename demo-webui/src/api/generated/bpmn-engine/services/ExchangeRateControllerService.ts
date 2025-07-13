/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { ExchangeRateDto } from '../models/ExchangeRateDto';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class ExchangeRateControllerService {
    /**
     * @returns ExchangeRateDto OK
     * @throws ApiError
     */
    public static getExchangeRates(): CancelablePromise<Array<ExchangeRateDto>> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/v1/exchange-rates',
        });
    }
}
