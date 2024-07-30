using System.ComponentModel;
using System.Net;
using System.Text.Json;

namespace MauiApp1;

public partial class Cart : ContentPage, INotifyPropertyChanged
{
    private string token;
    CartClass cart = new CartClass();
    private static HttpClient sharedClient = new()
    {
        BaseAddress = new Uri("http://wellwiredvase.ru:8080/api/"),
    };
    public event PropertyChangedEventHandler PropertyChanged;
    protected virtual void OnPropertyChanged(string propertyName)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }
    public double TotalPrice { get; set; }
    public Cart()
	{
		InitializeComponent();
		LoadCart();
	}
    public async void LoadCart()
    {
        Login admin = adminLogin();
        token = admin.token;
        sharedClient.DefaultRequestHeaders.Authorization = new System.Net.Http.Headers.AuthenticationHeaderValue("Bearer", token);
        cart = await getProducts(admin.id);
        BindingContext = this;
        collView.ItemsSource = cart.products;
        OnPropertyChanged(nameof(TotalPrice));

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
    async private Task<CartClass> getProducts(int id)
    {
        using HttpResponseMessage response = await sharedClient.GetAsync("accounts/"+id);
        var jsonResponse = await response.Content.ReadAsStringAsync();
        var a = JsonSerializer.Deserialize<Login>(jsonResponse);
        var b = a.cart;
        double total = 0;
        foreach (var product in b.products)
        {
            total += product.price;
        }
        TotalPrice = total;
        OnPropertyChanged(nameof(TotalPrice));
        return b;
    }
    private async void Del_butt(object sender, EventArgs e)
    {
        var butt = (Button)sender;
        Product sel_prod = (Product)butt.BindingContext;
        int id = (int)sel_prod.id;
        var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://wellwiredvase.ru:8080/api/cart/products/" + id);
        httpWebRequest.Headers["Authorization"] = "Bearer " + token;
        httpWebRequest.ContentType = "application/json";
        httpWebRequest.Method = "Delete";
        var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
        LoadCart();
    }
}