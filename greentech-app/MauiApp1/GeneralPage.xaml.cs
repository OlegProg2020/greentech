using System.Net;
using System.Text.Json;

namespace MauiApp1;

public partial class GeneralPage : ContentPage
{
    private string token;
    List<Product> products;
    private static HttpClient sharedClient = new()
    {
        BaseAddress = new Uri("http://wellwiredvase.ru:8080/api/products/"),
    };
    public GeneralPage()
	{
        InitializeComponent();
        loadProducts();
       
    }
    public async void loadProducts()
    {
        Login admin = adminLogin();
        token = admin.token;
        sharedClient.DefaultRequestHeaders.Authorization = new System.Net.Http.Headers.AuthenticationHeaderValue("Bearer", token);
        products = await getProducts();
        collView.ItemsSource = products;
        
    }
    async private Task<List<Product>> getProducts()
    {
        using HttpResponseMessage response = await sharedClient.GetAsync("search?size=10&page=0");
        var jsonResponse = await response.Content.ReadAsStringAsync();
        var a = JsonSerializer.Deserialize<List<Product>>(jsonResponse);
        return a;
    }
   

    private Login adminLogin()
    {
        var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://wellwiredvase.ru:8080/api/login");
        httpWebRequest.ContentType = "application/json";
        httpWebRequest.Method = "POST";

        using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
        {
            string json = "{\"phone\": \"" + "80000000000" + "\"," + "\n" +
                          "\"password\": \"" + "0000" + "\"}";

            streamWriter.Write(json);
        }

        var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
        var result = "";
        using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
        {
            result = streamReader.ReadToEnd();
        }
        Login? login = JsonSerializer.Deserialize<Login>(result);
        return login;
    }
    private async void TapGestureRecognizer_Tapped(object sender, TappedEventArgs e)
    {
        var vertStack = (VerticalStackLayout)sender;
        var product = (TapGestureRecognizer)vertStack.GestureRecognizers[0];
        int id = (int)product.CommandParameter;
        var a = products.Find(x => x.id == id);
        await Application.Current.MainPage.Navigation.PushModalAsync(new ProductPage(a, token), true);
    }
    private async void Buy_butt(object sender, EventArgs e)
    {
        var butt = (Button)sender;
        Product sel_prod = (Product)butt.BindingContext;
        int id = (int)sel_prod.id;
        var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://wellwiredvase.ru:8080/api/cart/products/"+id);
        httpWebRequest.Headers["Authorization"] = "Bearer " + token;
        httpWebRequest.ContentType = "application/json";
        httpWebRequest.Method = "POST";
        var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
    }
}