using System;
using System.Collections.Generic;

namespace Spacia.Web.Models
{
    public class DataEventosModel
    {

        public List<EventoModel> events { get; set; }
        public List<AmbienteModel> rooms { get; set; }
    }
}
