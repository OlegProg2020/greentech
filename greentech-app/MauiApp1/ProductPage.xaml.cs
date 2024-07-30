using System.Globalization;
using System.Net;
using System.Text.Json;

namespace MauiApp1;

public partial class ProductPage : ContentPage
{
    private Product prod { get; set; }
    private string token { get; set; }
    private static HttpClient sharedClient = new()
    {
        BaseAddress = new Uri("http://wellwiredvase.ru:8080/api/products/"),
    };

    public ProductPage(Product pot, string token1)
	{
		InitializeComponent();
        prod = pot;
		token = token1;
        BindingContext = prod;
	}
    private async void back_but_Clicked(object sender, EventArgs e)
    {
        await Shell.Current.GoToAsync("///GeneralPage");
    }
    private async void Buy_butt(object sender, EventArgs e)
    {
        var butt = (Button)sender;
        Product sel_prod = (Product)butt.BindingContext;
        int id = (int)sel_prod.id;
        var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://wellwiredvase.ru:8080/api/cart/products/" + id);
        httpWebRequest.Headers["Authorization"] = "Bearer " + token;
        httpWebRequest.ContentType = "application/json";
        httpWebRequest.Method = "POST";
        var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
    }
}