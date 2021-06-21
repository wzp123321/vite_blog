/// <reference path="./typings/common.d.ts"/>
/// <reference path="./typings/table.d.ts"/>

declare module "*.vue" {
  import { DefineComponent } from "vue";
  const component: DefineComponent<{}, {}, any>;
  export default component;
}



declare global {
  interface ImportMeta {
    env: Record<string, unknown>;
  }
}