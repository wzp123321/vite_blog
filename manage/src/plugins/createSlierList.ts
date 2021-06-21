import { routes } from "../routers/index";

/**
 * 生成侧边栏列表
 * @returns
 */
export const createSlideList = (): {
  path: string;
  name: string;
  icon: string;
}[] => {
  let list: any = [];
  if (routes && routes.length) {
    routes.forEach((item) => {
      if (item.meta && item.meta.hasSlide) {
        item.children?.forEach((childItem) => {
          if (childItem && childItem.meta && childItem.meta.isSlide) {
            const { path } = childItem;
            const { name, icon } = childItem.meta;
            list.push({
              path,
              name,
              icon,
            });
          }
        });
      }
    });
  }
  return list;
};
