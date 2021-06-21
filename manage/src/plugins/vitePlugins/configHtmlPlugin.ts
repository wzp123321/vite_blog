/**
 * Plugin to minimize and use ejs template syntax in index.html.
 * https://github.com/anncwb/vite-plugin-html
 */
 import type { Plugin } from 'vite';

 import html from 'vite-plugin-html';
 
 import pkg from '../../../package.json';
 import ServiceConfig from '../../config/constant';
 
 export function configHtmlPlugin() {
 
   const path = '/';
 
   const getAppConfigSrc = () => {
     return `${path || '/'}${ServiceConfig.GLOB_CONFIG_FILE_NAME}?v=${pkg.version}-${new Date().getTime()}`;
   };
 
   const htmlPlugin: Plugin[] = html({
     minify: true,
     inject: {
       // Inject data into ejs template
       injectData: {
         title: 'zpwan',
       },
       // Embed the generated app.config.js file
       tags: true
         ? [
             {
               tag: 'script',
               attrs: {
                 src: getAppConfigSrc(),
               },
             },
           ]
         : [],
     },
   });
   console.log('htmlPlugin', htmlPlugin);
   return htmlPlugin;
 }
 