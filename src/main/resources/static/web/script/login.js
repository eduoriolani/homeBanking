
const { createApp } = Vue;

console.log(Vue)


createApp({
  data() {
    return {
        email: "",
        password: "",
        firstName: "",
        lastName: "",
        showRegister: false,
        isRegisterParam: false

    }
  },
  created(){
    const urlParams = new URLSearchParams(window.location.search);
    this.isRegisterParam = urlParams.get('register') === 'true';
    if (this.isRegisterParam) {
      this.showRegister = true;
    }

  },
  methods : { 

    postClient() { 
        axios.post('/api/login','email=' + this.email + '&password=' + this.password ,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
              console.log(response);
              Swal.fire(
                'Login successful!',
                'Now take a look at your accounts.',
                'success'
              ).then(() => {
                window.location.href = "/web/pages/accounts.html";
              });
            })
            .catch(error => {
                console.log(error.response);
                Swal.fire(
                'Login failed!',
                error.response.data,
                'error'
              );
              this.email = "";
              this.password = "";
            });
    },
    registration(){
      axios.post('/api/clients','firstName='+ this.firstName +'&lastName='+ this.lastName +'&email=' + this.email +'&password='+ this.password,{headers:{'content-type':'application/x-www-form-urlencoded'}})
          .then(response => {
            console.log(response);
            Swal.fire({
              title: 'Register successful!',
              text: 'Please wait while you are being logged.',
              timer: 2000,
              showConfirmButton: false,
              timerProgressBar: true,
              onBeforeOpen: () => {
                Swal.showLoading();
              }
            }).then(() => {
              this.postClient();
            });
          })
          .catch(error => {
            console.log(error.response);
                Swal.fire(
                'Register failed!',
                error.response.data,
                'error'
              );
              this.firstName = "";
              this.lastName = "";
              this.email = "";
              this.password = "";
          })

    },

    showForm(){
        this.showRegister = !this.isRegisterParam;
    },

  }

}).mount('#app')