using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MauiApp1
{
    public class Login
    {
        public int id {  get; set; }
        public string phone {  get; set; }
        public string role {  get; set; }
        public string name { get; set; }
        public string token { get; set; }
        public CartClass cart { get; set; }
    }
    public class Product
    {
        public int id { get; set; }
        public string article { get; set; }
        public string name { get; set; }
        public double price { get; set; }
        public string description { get; set; }
        public Dictionary<string, string> characteristics { get; set; }
        public List<Image> images { get; set; }
    }
    public class addProp
    {
        //public ParameterResponseDto(id=5, name=Размер)
    }
    public class CartClass
    {
        public int id { get; set; }
        public List<Product> products { get; set; }
    }
    public class Image
    {
        public int id { get; set; }
        public string uri { get; set; }
    }
}
