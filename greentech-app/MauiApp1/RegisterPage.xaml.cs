using System.Net;
using System.Text;
using System.Text.Json;

namespace MauiApp1;

public partial class RegisterPage : ContentPage
{
	public RegisterPage()
	{
        InitializeComponent();
    }

    private async void Button_Clicked(object sender, EventArgs e)
    {
        var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://wellwiredvase.ru:8080/api/register");
        httpWebRequest.ContentType = "application/json";
        httpWebRequest.Method = "POST";

        using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
        {
            string json = "{\"phone\": \"" + phone.Text + "\"," + "\n" +
                          "\"name\": \"" + name.Text + "\"," + "\n" +
                          "\"password\": \"" + password.Text + "\"}";

            streamWriter.Write(json);
        }

        var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
        var result = "";
        using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
        {
            result = streamReader.ReadToEnd();
        }
        await Application.Current.MainPage.DisplayAlert("Тест", result.ToString(),"Отмена");
    }

    private async void back_but_Clicked(object sender, EventArgs e)
    {
        await Shell.Current.GoToAsync("///MainPage");
    }
}