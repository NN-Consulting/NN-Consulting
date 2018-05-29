using System;
namespace Spacia.Web.Models
{
    public class LoginModel
    {
        public string email { get; set; }
        public string message { get; set; }
        public string password { get; set; }
        public string error { get; set; }
        public DataModel data { get; set; }
        public ComunicacionModel comunicacion { get; set; }
    }
}
