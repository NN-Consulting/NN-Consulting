using System;
using System.Runtime.Serialization;
using Spacia.Web.Models;

namespace Spacia.Web.Clases
{
    public class MiContexto
    {
        [DataMember(Name = "sesion", Order = 1)]
        public LoginModel sesion { get; set; }
    }
}
