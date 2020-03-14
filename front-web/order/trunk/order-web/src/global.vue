/*
 * 
 * 全局常量定义
 */
<script>
import axios from 'axios';

const REQUEST_HOST =  process.env.NODE_ENV === 'development' ? "http://127.0.0.1:9800/order/":"";
// if(process.env.NODE_ENV === 'development'){
// 	REQUEST_HOST = "http://localhost:9992/workflow/";
// }

const CONTEXT_PATH = '';//'/survey/';

 function _post_QueryData(url,paramObj,exeHandle){
 	let postUrl = REQUEST_HOST + url;
    axios.post(postUrl,paramObj).then((res) => {
 	 	   exeHandle(res); //当数据返回时，回调业务模块中的数据处理函数
     }).catch(function(error){
     	console.log(error)
     })
 }

 function _get_QueryData(url,exeHandle){
 	let postUrl = REQUEST_HOST + url;
    axios.get(postUrl).then((res) => {
       exeHandle(res);
    }).catch(function(error){
    	console.log(error);
    })
 }

export default
{
  REQUEST_HOST,
  CONTEXT_PATH,
  _get_QueryData,
  _post_QueryData
}
</script>