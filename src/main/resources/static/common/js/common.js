const commonLib = {
    /**
     * ajax 공통기능
     * responseType : 응답데이터 타입 ( text 는 text로 , 그 외는 json으로 응답 )
     */
    ajaxLoad(url, method = "GET", data, headers, responseType ) {
        if(!url) {

            return;
        }
        const csrfToken = document.querySelector("meta[name='csrf_token']")?.content?.trim();
        const csrfHeader = document.querySelector("meta[name='csrf_header']")?.content?.trim() ;
        let rootUrl = document.querySelector("meta[name='rootUrl']")?.content?.trim() ?? '';
        rootUrl = rootUrl === '/' ? '' : rootUrl;

        url = location.protocol + "//" + location.host + rootUrl + url;
        method = method.toUpperCase();
        if(method === "GET") {
            data = null;
        }
        if(!(data instanceof FormData) && typeof data !== "string" && data instanceof Object) {
            data = JSON.stringify(data);
        }
        if(csrfHeader && csrfToken) {
            headers = headers ?? {};
            headers[csrfHeader] = csrfToken;
        }

        const options = {
            method
        };
        if(data) options.body = data;
        if(headers) options.headers = headers;

        return new Promise((resolve, reject) => {
            fetch(url, options)
                .then(res=> responseType === 'text' ? res.text() : res.json())
                .then(data => resolve(data))
                .catch(err => reject(err));
        });
    }
};