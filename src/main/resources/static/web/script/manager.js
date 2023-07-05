
const { createApp } = Vue;

console.log(Vue)


createApp({
  data() {
    return {
      clients: [],
      rest: [],
      clientData: {
        firstName: "",
        lastName: "",
        email: "",
      },
      restResponse: null,


    }
  },
  created(){
    this.loadData();
  },
  methods : {
    loadData() {
        axios.get("http://localhost:8080/api/clients")
        .then(response => {
          console.log(response);
          this.clients = response.data;
          console.log(this.clients)
          this.restResponse = response.data;
        })
        .catch(error => {
          console.error(error);
        });
    },
    addClient() {
    if(this.clientData.firstName !== "" && this.clientData.lastName !== "" && this.clientData.email !== ""){
        this.postClient();
    }else{
        alert("Please complete all the fields")
    }

    },
    postClient() {
        axios.post("http://localhost:8080/api/clients", this.clientData)
           .then( res => {
          
           this.loadData();
           this.clientData.firstName = "";
           this.clientData.lastName = "";
           this.clientData.email = "";
        })
            .catch(error => {
               console.error(error);
            });
    },
    deleteClient(id){
      axios.delete(id)
      .then((response)=> {
        console.log(response.data);
        this.loadData();
      })
      .catch(error => console.error(error))
    }

  }

}).mount('#app')