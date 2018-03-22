//let head = store.state.head; // 请求头
var token = '';

function axiosGet(url, data, call) {
  url = head + url + '?changes=' + new Date().getTime();
  axios.get(url, {
      params: data
    })
    .then(function(res) {
      call(res);
    })
    .catch(function(err) {
      console.log(err)
    })
}

function axiosPost(url, data, call) {
  url = head + url;
  axios.post(url, data)
    .then(function(res) {
      call(res)
    }).catch(function(err) {
      console.log(err)
    })
}

function axiosDelete(url, data, call) {
  url = head + url;
  axios.delete(url, {
      params: data
    })
    .then(function(res) {
      call(res);
    })
    .catch(function(err) {
      console.log(err)
    })
}

function progressfile(url, data, call) {
  url = head + url;
  var xhr = new XMLHttpRequest();
  xhr.open('post', url, true);
  xhr.onload = function() {

  }
  xhr.upload.onprogress = function(event) {
    call(event)
  }
  xhr.send(data)
}