using Android.App;
using Android.Runtime;

namespace MauiApp1
{                                  // connect to local service on the
    [Application(UsesCleartextTraffic = true)]  // emulator's host for debugging,

    public class MainApplication : MauiApplication
    {
        public MainApplication(IntPtr handle, JniHandleOwnership ownership)
            : base(handle, ownership)
        {
        }

        protected override MauiApp CreateMauiApp() => MauiProgram.CreateMauiApp();
    }
}