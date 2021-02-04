/* jshint esversion: 6 */

import axios from "axios";

const TOKEN = '';

export function beginSearch(seed, limit) {
    axios.post('http://localhost:8080/search', seed, limit)
      .then(function (response) {
          window.location.href = '/';
      })
      .catch(function (error) {
          console.error(error.response);
      });
}

// promise version
/*
export let beginSearch = (seed, limit) => new Promise((resolve, reject) => {
    axios.post('http://localhost:8080/search', seed, limit )
        .then(function (response) {
            console.log(response.data);
            resolve(response.data);
        })
        .catch(function (error) {
            console.log("o no");
            reject(error.response);
        });
});
*/

// function setupAxiosInterceptors(token) {
//     console.log(token);
//     axios.interceptors.request.use(
//         (config) => {
//             console.log(token);
//             console.log("config");
//             if (isUserLoggedIn()) {
//                 config.headers['authorization'] = token;
//             }
//             console.log("asg");
//             return config;
//         }, (error) => {
//             // Do something with request error
//             console.log('nooooooooooassasssssf')
//             return Promise.reject(error);
//         }
//     );
// }
