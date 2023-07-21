const { createApp } = Vue;
console.log(Vue);

createApp({
  data() {
    return {
      client: {},
      logged: true,
      activeAccounts: [],
      loans: [],
      selectedLoan: "",
      loan: {
        loanName: "",
        payment: "",
        amount: "",
        destinationAccount: "",
        taxPercentage: ""
      }
    };
  },
  created() {
    this.loadData();
    this.getLoans();
  },
  methods: {
    loadData() {
      axios
        .get("/api/clients/current")
        .then((response) => {
          this.client = response.data;
          this.activeAccounts = this.client.accounts.filter(acc => acc.active == true);
          console.log(this.client);
          this.logged = true;

          this.format = new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD',
        });

        })
        .catch((error) => {
          this.logged = false;
        });
        
    },
    getLoans(){
        axios
        .get("/api/loans")
        .then(response => {
            console.log(response.data);
            this.loans = response.data;

        })
        .catch(error => {
            console.log(error.response.data);
        })
    },
    loanForm(){
      this.loan.loanName = this.selectedLoan.name;
        axios
        .post("/api/loans", this.loan)
        .then(response => {
          Swal.fire(
            'Loan request succeeded!',
            'You can see your updated balance.',
            'success'
          ).then(() => {
            window.location.href = "/web/pages/accounts.html";
          });
        })
        .catch(error => {
          console.log(error.response);
          Swal.fire(
            'Request failed!',
            error.response.data,
            'error'
          );
        });
    },
    confirmLoanRequest(){
      Swal.fire({
          title: 'Are you sure to request a new loan?',
          text: "You won't be able to revert this!",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes!'
        }).then((result) => {
          if(result.isConfirmed){
          this.loanForm();
          } else {
            Swal.fire(
              'Request failed!',
              'Please try again',
              'error')
          }
        })    
  },
    
    logOut(){
      axios.post('/api/logout').then(response => console.log('signed out!!!'))
    },
    mostrarMenu() {
      // Mostrar la ventana emergente
      document.getElementById("myModal").style.display = "block";
    },
    cerrarModal() {
      // Cerrar la ventana emergente
      document.getElementById("myModal").style.display = "none";
    },
  
  },
}).mount("#app");
