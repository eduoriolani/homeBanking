
const { createApp } = Vue;

console.log(Vue)


createApp({
  data() {
    return {
      clients: [],
      rest: [],
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        newLoan: {
          name: "",
          maxAmount: "",
          payments: [],
          taxPercentage: ""
        },
      restResponse: null,


    }
  },
  created(){
    this.loadData();
  },
  methods : {
    loadData() {
        axios.get("/api/clients")
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
    if(this.firstName !== "" && this.lastName !== "" && this.email !== ""){
        this.postClient();
    }else{
        alert("Please complete all the fields")
    }

    },
    postClient() {
      axios.post('/api/clients','firstName='+ this.firstName +'&lastName='+ this.lastName +'&email=' + this.email +'&password='+ this.password,{headers:{'content-type':'application/x-www-form-urlencoded'}})
           .then( res => {
          
           this.loadData();
           this.firstName = "";
           this.lastName = "";
           this.email = "";
           this.password = "";
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
    },
    confirmLoan(){
      Swal.fire({
        title: 'Are you sure to create a new Loan?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes!'
      }).then((result) => {
        if(result.isConfirmed){
        this.createLoan();
        } else {
          Swal.fire(
            'Request failed!',
            'Please try again',
            'error')
        }
      })    
    },
    createLoan(){
      axios
      .post('/api/loans/create', this.newLoan)
      .then(response => {
        Swal.fire(
          'Loan payment succeeded!',
          'You can see your updated balance.',
          'success'
        ).then(() => {
          this.loadData();
        });
      })
      .catch(error => {
        console.log(error);
      })
    }

  }

}).mount('#app')