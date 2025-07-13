/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { CurrencyExchangeDto } from '../models/CurrencyExchangeDto';
import type { CurrencyExchangeRequest } from '../models/CurrencyExchangeRequest';
import type { CurrencyExchangeStartResponse } from '../models/CurrencyExchangeStartResponse';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class CurrencyExchangeControllerService {
    /**
     * @returns CurrencyExchangeDto OK
     * @throws ApiError
     */
    public static getCurrencyExchanges(): CancelablePromise<Array<CurrencyExchangeDto>> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/v1/currency-exchange',
        });
    }
    /**
     * @param requestBody
     * @returns CurrencyExchangeStartResponse OK
     * @throws ApiError
     */
    public static processCurrencyExchangeRequest(
        requestBody: CurrencyExchangeRequest,
    ): CancelablePromise<CurrencyExchangeStartResponse> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/v1/currency-exchange',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
    /**
     * @param id
     * @returns CurrencyExchangeDto OK
     * @throws ApiError
     */
    public static getCurrencyExchange(
        id: string,
    ): CancelablePromise<CurrencyExchangeDto> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/v1/currency-exchange/{id}',
            path: {
                'id': id,
            },
        });
    }
}
