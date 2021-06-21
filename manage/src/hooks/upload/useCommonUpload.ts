import axios from "axios";
const env = process.env.NODE_ENV;
import { useWebCommonSetting } from "../common/useWebCommonSetting";
const url =
  env === "production"
    ? "https://server.zpwan-yz.com/blogManage/filemodule/web/upload"
    : "http://127.0.0.1:9527/api/file/upload";

/**
 * 文件上传
 * @returns
 */
export const useCommonUpload = () => {
  const { Deffer } = useWebCommonSetting();
  let deffer;
  const handleFileUpload = async (file: File) => {
    deffer = new Deffer();
    let formData = new FormData();
    formData.append("file", file);

    try {
      const res = await axios({
        method: "post",
        url,
        headers: {
          "Content-Type": "multipart/form-data",
          "s-Auth-Token":
            localStorage.getItem("manage_token") === null
              ? ""
              : localStorage.getItem("manage_token"),
        },
        data: formData,
      });
      if (res) {
        deffer.resolve(res.data.data.url);
      }
    } catch (error) {
      deffer.reject(error);
    }
    return deffer.promise;
  };
  return { handleFileUpload };
};
