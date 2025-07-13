/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class AsyncApiControllerService {
    /**
     * @returns string OK
     * @throws ApiError
     */
    public static asyncApiYaml(): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/springwolf/docs.yaml',
            errors: {
                400: `Bad Request`,
                404: `Not Found`,
                409: `Conflict`,
            },
        });
    }
    /**
     * @returns string OK
     * @throws ApiError
     */
    public static asyncApiJson(): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/springwolf/docs',
            errors: {
                400: `Bad Request`,
                404: `Not Found`,
                409: `Conflict`,
            },
        });
    }
    /**
     * @returns string OK
     * @throws ApiError
     */
    public static asyncApiJson1(): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/springwolf/docs.json',
            errors: {
                400: `Bad Request`,
                404: `Not Found`,
                409: `Conflict`,
            },
        });
    }
}
