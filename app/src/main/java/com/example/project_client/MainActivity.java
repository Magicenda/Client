package com.example.project_client;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    Button getByIdStock, getAllStock, getAllNameStock, getAllPrice150, PriceAndCount, getAllAccountingByStockId, getStockByAccountingId, getAllAccounting, getByNameA, getByDefective;
    EditText editText;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getByIdStock = (Button) findViewById(R.id.getByidStock);
        getAllStock = (Button) findViewById(R.id.getAllStock);
        getAllNameStock = (Button) findViewById(R.id.getAllNameStock);
        getAllPrice150 = (Button) findViewById(R.id.getAllPrice150);
        PriceAndCount = (Button) findViewById(R.id.CountAndPrice);
        getAllAccountingByStockId = (Button) findViewById(R.id.getAccountingByStockId);
        getStockByAccountingId = (Button) findViewById(R.id.getStockByAccountingId);
        getAllAccounting = (Button) findViewById(R.id.getAllAccounting);
        getByNameA = (Button) findViewById(R.id.getByNameA);
        getByDefective = (Button) findViewById(R.id.getByDefective);
        editText = (EditText) findViewById(R.id.Id);

        getByIdStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText.getText().toString().trim();
                if (id.isEmpty() || !isValidId(id)) {
                    showMessage("Error", "Please enter a valid ID.");
                    return;
                }

                String URL = "http://10.0.2.2:8080/stock/getById/" + id;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request = new Request.Builder()
                                    .url(URL)
                                    .get()
                                    .build();

                            OkHttpClient client = new OkHttpClient();
                            Call call = client.newCall(request);

                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            }

                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            Stock stock = mapper.readValue(serverResponse, Stock.class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("id: " + stock.getId() + "\n");
                                    stringBuilder.append("Name: " + stock.getName() + "\n");
                                    stringBuilder.append("Count: " + stock.getCount() + "\n");
                                    stringBuilder.append("Price: " + stock.getPrice() + "\n");

                                    showMessage("getById", stringBuilder.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace(); // Log the exception
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("Error", "Failed to fetch data. Please try again.");
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        getAllStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL = "http://10.0.2.2:8080/stock/getAll";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Request request = new Request.Builder()
                                .url(URL)
                                .get()
                                .build();

                        OkHttpClient client = new OkHttpClient();
                        Call call = client.newCall(request);

                        Response response;
                        try {
                            response = call.execute();
                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            Stock[] stocks = mapper.readValue(serverResponse, Stock[].class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (Stock s : stocks) {
                                        stringBuilder.append("id: ").append(s.getId()).append("\n");
                                        stringBuilder.append("Name: " + s.getName() + "\n");
                                        stringBuilder.append("Count: " + s.getCount() + "\n");
                                        stringBuilder.append("Price: " + s.getPrice() + "\n" + "\n");
                                    }
                                    showMessage("all", stringBuilder.toString());
                                }
                            });


                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        });

        getAllNameStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL = "http://10.0.2.2:8080/stock/getAllName";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Request request = new Request.Builder()
                                .url(URL)
                                .get()
                                .build();

                        OkHttpClient client = new OkHttpClient();
                        Call call = client.newCall(request);

                        Response response;
                        try {
                            response = call.execute();
                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            String[] stocks = mapper.readValue(serverResponse, String[].class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (String s : stocks) {
                                        stringBuilder.append("Name: " + s + "\n" + "\n");
                                    }
                                    showMessage("all", stringBuilder.toString());
                                }
                            });


                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        });

        getAllPrice150.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://10.0.2.2:8080/stock/getAllPrice150";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Request request = new Request.Builder()
                                .url(URL)
                                .get()
                                .build();

                        OkHttpClient client = new OkHttpClient();
                        Call call = client.newCall(request);

                        Response response;
                        try {
                            response = call.execute();
                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            Stock[] stocks = mapper.readValue(serverResponse, Stock[].class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (Stock s : stocks) {
                                        stringBuilder.append("id: ").append(s.getId()).append("\n");
                                        stringBuilder.append("Name: " + s.getName() + "\n");
                                        stringBuilder.append("Count: " + s.getCount() + "\n");
                                        stringBuilder.append("Price: " + s.getPrice() + "\n" + "\n");
                                    }
                                    showMessage("all", stringBuilder.toString());
                                }
                            });


                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        });
        PriceAndCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://10.0.2.2:8080/stock/getAllCountAndPrice";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Request request = new Request.Builder()
                                .url(URL)
                                .get()
                                .build();

                        OkHttpClient client = new OkHttpClient();
                        Call call = client.newCall(request);

                        Response response;
                        try {
                            response = call.execute();
                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            Stock[] stocks = mapper.readValue(serverResponse, Stock[].class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (Stock s : stocks) {
                                        stringBuilder.append("id: ").append(s.getId()).append("\n");
                                        stringBuilder.append("Name: " + s.getName() + "\n");
                                        stringBuilder.append("Count: " + s.getCount() + "\n");
                                        stringBuilder.append("Price: " + s.getPrice() + "\n" + "\n");
                                    }
                                    showMessage("all", stringBuilder.toString());
                                }
                            });

                    } catch(
                    IOException e)

                    {
                        throw new RuntimeException(e);
                    }
                }
            }).

            start();
        }
    });
        getAllAccountingByStockId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText.getText().toString().trim();
                if (id.isEmpty() || !isValidId(id)) {
                    showMessage("Error", "Please enter a valid ID.");
                    return;
                }

                String URL = "http://10.0.2.2:8080/stock/getAllAccountingByStockId/" + id;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request = new Request.Builder()
                                    .url(URL)
                                    .get()
                                    .build();

                            OkHttpClient client = new OkHttpClient();
                            Call call = client.newCall(request);

                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            }

                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            Accounting[] accountings = mapper.readValue(serverResponse, Accounting[].class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (Accounting a : accountings) {
                                        stringBuilder.append("id: ").append(a.getId()).append("\n");
                                        stringBuilder.append("Name: " + a.getName() + "\n");
                                        stringBuilder.append("Count: " + a.getCount() + "\n");
                                        stringBuilder.append("Defective: " + a.getDefective() + "\n" + "\n");
                                    }
                                    showMessage("getAcc", stringBuilder.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace(); // Log the exception
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("Error", "Failed to fetch data. Please try again.");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        getStockByAccountingId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText.getText().toString().trim();
                if (id.isEmpty() || !isValidId(id)) {
                    showMessage("Error", "Please enter a valid ID.");
                    return;
                }

                String URL = "http://10.0.2.2:8080/accounting/getStockByAccountingId/" + id;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request = new Request.Builder()
                                    .url(URL)
                                    .get()
                                    .build();

                            OkHttpClient client = new OkHttpClient();
                            Call call = client.newCall(request);

                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            }

                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            Stock stock = mapper.readValue(serverResponse, Stock.class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("id: ").append(stock.getId()).append("\n");
                                    stringBuilder.append("Name: " + stock.getName() + "\n");
                                    stringBuilder.append("Count: " + stock.getCount() + "\n");
                                    stringBuilder.append("Price: " + stock.getPrice() + "\n\n");

                                    showMessage("getStockByAccounting", stringBuilder.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace(); // Log the exception
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("Error", "Failed to fetch data. Please try again.");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        getAllAccounting.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){

        String URL = "http://10.0.2.2:8080/accounting/getAll";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(URL)
                        .get()
                        .build();

                OkHttpClient client = new OkHttpClient();
                Call call = client.newCall(request);

                Response response;
                try {
                    response = call.execute();
                    String serverResponse = response.body().string();
                    ObjectMapper mapper = new ObjectMapper();
                    Accounting[] accountings = mapper.readValue(serverResponse, Accounting[].class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (Accounting s : accountings) {
                                stringBuilder.append("id: ").append(s.getId()).append("\n");
                                stringBuilder.append("Name: " + s.getName() + "\n");
                                stringBuilder.append("Count: " + s.getCount() + "\n");
                                stringBuilder.append("Defective: " + s.getDefective() + "\n" + "\n");
                            }
                            showMessage("all", stringBuilder.toString());
                        }
                    });


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    });
        getByNameA.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        String URL = "http://10.0.2.2:8080/accounting/getByName";
        new Thread(new Runnable() {
            @Override

            public void run() {
                Request request = new Request.Builder()
                        .url(URL)
                        .get()
                        .build();

                OkHttpClient client = new OkHttpClient();
                Call call = client.newCall(request);

                Response response;
                try {
                    response = call.execute();
                    String serverResponse = response.body().string();
                    ObjectMapper mapper = new ObjectMapper();
                    Accounting[] accountings = mapper.readValue(serverResponse, Accounting[].class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (Accounting s : accountings) {
                                stringBuilder.append("id: ").append(s.getId()).append("\n");
                                stringBuilder.append("Name: " + s.getName() + "\n");
                                stringBuilder.append("Count: " + s.getCount() + "\n");
                                stringBuilder.append("Defective: " + s.getDefective() + "\n" + "\n");
                            }
                            showMessage("all", stringBuilder.toString());
                        }
                    });


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    });
        getByDefective.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        String URL = "http://10.0.2.2:8080/accounting/getByDefective";
        new Thread(new Runnable() {
            @Override

            public void run() {
                Request request = new Request.Builder()
                        .url(URL)
                        .get()
                        .build();

                OkHttpClient client = new OkHttpClient();
                Call call = client.newCall(request);

                Response response;
                try {
                    response = call.execute();
                    String serverResponse = response.body().string();
                    ObjectMapper mapper = new ObjectMapper();
                    Accounting[] accountings = mapper.readValue(serverResponse, Accounting[].class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (Accounting s : accountings) {
                                stringBuilder.append("id: ").append(s.getId()).append("\n");
                                stringBuilder.append("Name: " + s.getName() + "\n");
                                stringBuilder.append("Count: " + s.getCount() + "\n");
                                stringBuilder.append("Defective: " + s.getDefective() + "\n" + "\n");
                            }
                            showMessage("all", stringBuilder.toString());
                        }
                    });


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    });
}

    public void showMessage(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
    private boolean isValidId(String id) {
        // Add your validation logic here
        // For example, check if the ID contains only digits
        return id.matches("\\d+");
    }
}
