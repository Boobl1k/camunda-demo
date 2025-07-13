/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class SettingsControllerService {
    /**
     * @param requestBody
     * @returns any OK
     * @throws ApiError
     */
    public static changeTransactionHandlingSetting(
        requestBody: 'HANDLE_SUCCESSFULLY' | 'HANDLE_NEXT_DEPOSIT_WITH_FAILURE',
    ): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/api/v1/test/settings/transaction-handling',
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
