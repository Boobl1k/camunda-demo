/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { CreateOtpRequest } from '../models/CreateOtpRequest';
import type { CreateOtpResponse } from '../models/CreateOtpResponse';
import type { SolveOtpRequest } from '../models/SolveOtpRequest';
import type { SolveOtpResponse } from '../models/SolveOtpResponse';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class OtpMockControllerService {
    /**
     * @param requestBody
     * @returns SolveOtpResponse OK
     * @throws ApiError
     */
    public static solveOtp(
        requestBody: SolveOtpRequest,
    ): CancelablePromise<SolveOtpResponse> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/api/v1/otp/solve',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
    /**
     * @param requestBody
     * @returns CreateOtpResponse OK
     * @throws ApiError
     */
    public static createOtp(
        requestBody: CreateOtpRequest,
    ): CancelablePromise<CreateOtpResponse> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/v1/otp',
            body: requestBody,
            mediaType: 'application/json',
        });
    }
}
