namespace MauiApp1
{
    public partial class AppShell : Shell
    {
        public AppShell()
        {
            InitializeComponent();
        }
        private void Shell_Navigated(object sender, ShellNavigatedEventArgs e)
        {
            // Обработка события CurrentPageChanged
            if (CurrentPage is Cart cartPage)
            {
                // Вызов метода обновления данных на странице Cart
                cartPage.LoadCart();
            }
        }
    }
}