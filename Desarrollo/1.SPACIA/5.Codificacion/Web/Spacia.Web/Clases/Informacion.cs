using System;
using System.Runtime.Serialization;

namespace Spacia.Web.Clases
{
    [Serializable]
    public class Informacion
    {
        [DataMember(Name = "miContexto", Order = 2)]
        public MiContexto MiContexto { get; set; }

        [DataMember(Name = "ruta", Order = 1)]
        public string Ruta { get; set; }
    }
}
