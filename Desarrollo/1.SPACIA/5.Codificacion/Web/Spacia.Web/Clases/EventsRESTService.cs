using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Spacia.Web.Models;

namespace Spacia.Web.Clases
{
    public class EventsRESTService
    {
        public List<AgendamientoModel> GetEvents()
        {
            string uri = "https://jsonplaceholder.typicode.com/posts/";

            using (WebClient webClient = new WebClient())
            {
                return JsonConvert.DeserializeObject<List<AgendamientoModel>>(
                    webClient.DownloadString(uri)
                );
            }
        }

        public List<AgendamientoModel> PostEvents(string data)
        {
            string uri = "https://jsonplaceholder.typicode.com/posts/";

            using (WebClient webClient = new WebClient())
            {
                try
                {
                    webClient.Headers[HttpRequestHeader.ContentType] = "application/x-www-form-urlencoded";
                    string htmlResult = webClient.UploadString(uri, data);
                    AgendamientoModel flight = new AgendamientoModel();
                    flight = JsonConvert.DeserializeObject<AgendamientoModel>(htmlResult);
                    return new List<AgendamientoModel>();
                }
                catch(Exception e)
                {
                    throw e;
                }
            }
        }
    }
}
