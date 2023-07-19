const { createApp } = Vue;

createApp({
  data() {
    return {
      client: {},
      accounts: [],
      activeAccounts: [],
      clientLoans: [],
      loans: [],
      balance: 0,
      type: null,
    };
  },
  created() {
    this.loadData();
    this.getLoans();
    AOS.init();
  },
  methods: {
    loadData() {
      axios
        .get("/api/clients/current")
        .then((response) => {
          this.client = response.data;
          this.accounts = this.client.accounts;
          this.accounts.sort((a,b)=> a.balance-b.balance);
          this.activeAccounts = this.accounts.filter(account => account.active == true);
          this.clientLoans = this.client.loans;
          console.log(this.clientLoans);
          this.format = new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD',
        });
        this.accounts.forEach( e => {
            e.balance = this.format.format(e.balance)
        })
        this.loans.forEach( e => {
          e.amount = parseInt(e.amount);
        })
        console.log("hola");
        // this.loans.forEach( e => {
        //     e.amount = this.format.format(e.amount)
        // })
        })
        .catch((error) => {
          console.error(error);
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
    selectType(){
      Swal.fire({
        title: `Let's create an account`,
        text: "Please, select the account type!",
        icon: 'warning',
        input: 'select',
        inputOptions: {
        Saving: 'Saving account',
        Checking: 'Checking account'
        },
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes!'
      }).then((result) => {
        if(result.isConfirmed){
        const selectedType = result.value;
        this.createAccount(selectedType);
        } else {
          Swal.fire(
            'Account creation failed!',
            'Please try again',
            'error')
        }
      })    
    },
    createAccount(selectedType){
      this.type = selectedType;
      axios
      .post('/api/clients/current/accounts', `type=${this.type}`)
      .then(response => {
        Swal.fire(
          'Account created!',
          'Your account has been created successfully!',
          'success'
        );
        this.loadData();
      })
      .catch(error => {
        Swal.fire(
          'Account creation failed!',
          'Please try again',
          'error'
        );
      });
    },
    deleteAccount(id){
      axios
      .patch('/api/clients/current/accounts', `id=${id}`)
      .then(response => {
        console.log(response.data);
        this.loadData();
      })
      .catch(error => {
        console.log(error.response.data);
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
