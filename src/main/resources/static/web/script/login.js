
const { createApp } = Vue;

console.log(Vue)


createApp({
  data() {
    return {
        email: "",
        password: "",
        firstName: "",
        lastName: "",
        showRegister: false

    }
  },
  created(){

  },
  methods : { 
    logClient() {
      if(this.email !== "" && this.password !== ""){
          this.postClient();
      }else{
          this.logError();
      }
    },
    registerClient(){
      if(this.emailRegister !== "" && this.passwordRegister !== "" && this.firstName !== "" && this.lastName !== ""){
        this.registration();
      } else{
        this.logError();
      }
    },  
    postClient() { 
        axios.post('/api/login','email=' + this.email + '&password=' + this.password ,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                console.log("Signed in!!")
                this.logSuccessful();
                
            })
            .then(response => {
              window.location.href = "../pages/accounts.html";
            })
            .catch(error => {
                this.logError();
                this.email = "";
                this.password = "";
            });
    },
    registration(){
      axios.post('/api/clients','firstName='+ this.firstName +'&lastName='+ this.lastName +'&email=' + this.email +'&password='+ this.password ,{headers:{'content-type':'application/x-www-form-urlencoded'}})
          .then(response => {
            console.log('registered')
            this.logSuccessful();
            this.logClient();
          })
          .catch(error => {
            this.logError();
                this.email = "";
                this.password = "";
                this.firstName = ""
                this.lastName = ""
          })

    },

    showForm(){
        this.showRegister = true
    },
    logSuccessful() {
        // Mostrar la ventana emergente
        document.getElementById('loginModal').style.display = 'block';
      },
    logError() {
        // Mostrar la ventana emergente
        document.getElementById('errorModal').style.display = 'block';
      },
      
    closeModal() {
        // Cerrar la ventana emergente
        document.getElementById('loginModal').style.display = 'none';
        document.getElementById('errorModal').style.display = 'none';
      },
    

  }

}).mount('#app')