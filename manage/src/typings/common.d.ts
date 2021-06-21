/**
 * 公共模块
 */
declare namespace CommonModule {
  interface SliderInfo {
    path: string;
    name: string;
    icon: string;
  }
  interface FormRuleInfo {
    [key: string]: {
      required?: boolean;
      message: string;
      trigger: string;
      min?: number;
      max?: number;
    }[];
  }

  interface PaginationParams {
    page: number;
    limit?: number;
    total?: number;
    [key: string]: string | number;
  }
}

/**
 * http请求
 */
declare namespace HttpService {
  interface Responseive<T> {
    code: number;
    message: string;
    data: T;
  }
  interface HttpListResponse<T> {
    list: T;
    total: number;
  }
  interface FileUploadInfo {}
}

declare namespace SignForm {
  /**
   * @param username 用户名
   * @param password 密码
   */
  interface SignFormInfo {
    username: string;
    password: string;
  }
  /**
   * @param name 用户名
   * @param id
   * @param token
   */
  interface UserInfo {
    name: string;
    id: number;
    token: string;
  }
}
