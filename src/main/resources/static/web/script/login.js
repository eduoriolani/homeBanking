
const { createApp } = Vue;

console.log(Vue)


createApp({
  data() {
    return {
      clientData: {
        email: "",
        password: "",
      },


    }
  },
  created(){

  },
  methods : {
    
    logClient() {
    if(this.clientData.email !== "" && this.clientData.password !== ""){
        this.postClient();
    }else{
        alert("Please complete all the fields")
    }

    },
    
    postClient() {
        
        axios.post('/api/login','email=' + this.clientData.email + '&password=' + this.clientData.password ,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                console.log("Signed in!!")
                this.logSuccessful();
                window.location.href = "../pages/accounts.html";
            })
            .catch(error => {
                this.logError();
                this.clientData.email = "";
                this.clientData.password = "";
            });
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