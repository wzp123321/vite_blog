declare namespace TableListManage {
  interface AdminVoInfo {
    createTime: number;
    id: number;
    modifyTime: number;
    name: string;
    password: string;
    salt: string;
  }

  interface EnumVoInfo {
    code: number;
    createTime: number;
    id: number;
    modifyTime: number;
    title: string;
  }
}
