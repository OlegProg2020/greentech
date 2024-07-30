using static Microsoft.Maui.ApplicationModel.Permissions;
using System.Net;
using System.Xml.Linq;

namespace MauiApp1
{
    public partial class MainPage : ContentPage
    {

        public MainPage()
        {
            InitializeComponent();
        }

        private async void Button_Clicked(object sender, EventArgs e)
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://wellwiredvase.ru:8080/api/login");
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Method = "POST";

            using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
            {
                string json = "{\"phone\": \"" + phone.Text + "\"," + "\n" +
                              "\"password\": \"" + password.Text + "\"}";

                streamWriter.Write(json);
            }

            var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
            var result = "";
            using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
            {
                result = streamReader.ReadToEnd();
            }
            await Application.Current.MainPage.DisplayAlert("Тест", result.ToString(), "Отмена");
        }

        private async void Reg_but(object sender, EventArgs e)
        {
            await Application.Current.MainPage.Navigation.PushModalAsync(new RegisterPage(), true);
        }
    }
}