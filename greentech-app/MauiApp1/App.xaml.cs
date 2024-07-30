namespace MauiApp1
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();
            Routing.RegisterRoute("MainPage", typeof(MainPage));
            Routing.RegisterRoute("RegisterPage", typeof(RegisterPage));
            Routing.RegisterRoute("GeneralPage", typeof(GeneralPage));
            Routing.RegisterRoute("Cart", typeof(Cart));
            Routing.RegisterRoute("Account", typeof(Account));
            Routing.RegisterRoute("Product", typeof(ProductPage));
            MainPage = new AppShell();
        }
    }
}