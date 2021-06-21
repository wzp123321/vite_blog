
/**
 * 全局暴露环境变量
 * @returns 环境变量
 */
export const useGlobalEnv = () => {
  const env = import.meta.env.MODE;
  return { env };
};
