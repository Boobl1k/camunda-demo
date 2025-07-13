/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { PerformTransactionRequest } from '../models/PerformTransactionRequest';
import type { PerformTransactionResponse } from '../models/PerformTransactionResponse';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class AccountControllerService {
    /**
     * @param userId
     * @param accountId
     * @param requestBody
     * @returns PerformTransactionResponse OK
     * @throws ApiError
     */
    public static performTransaction(
        userId: string,
        accountId: string,
        requestBody: PerformTransactionRequest,
    ): CancelablePromise<PerformTransactionResponse> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/v1/users/{userId}/accounts/{accountId}/perform-transaction',
            path: {
                'userId': userId,
                'accountId': accountId,
            },
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad Request`,
                404: `Not Found`,
                409: `Conflict`,
            },
        });
    }
}
