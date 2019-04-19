package com.example.nsa.scoreboard;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Button connect,ta,rm,dis;
    ListView deviceList;
    String address;
    String tar = "0";
    TextView conShow,conDis;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    public static String EXTRA_ADDRESS = "device_address";
    private BluetoothAdapter myBluetooth = null;
    private BluetoothSocket btSocket = null;
    private Set pairedDevices;
    int r=0;
    int w=0;
    int ext=0;
    int nb=0;
    int overs=0;
    int b=0;
    int wik=0;
    int target =0;
    int flag =0;
    int flag2 =0;
    TextView rr;

    TextView tB1,tB2,tshow,ov,wbShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button dot=(Button) findViewById(R.id.dot);
        Button one=(Button) findViewById(R.id.one);
        Button two = (Button) findViewById(R.id.two);
        Button three=(Button) findViewById(R.id.three);
        Button four=(Button) findViewById(R.id.four);
        Button six = (Button) findViewById(R.id.six);
        Button wbA = (Button) findViewById(R.id.wbA);
        Button wbM=(Button) findViewById(R.id.wbM);
        Button nbA =(Button) findViewById(R.id.nbA);
        Button nbM=(Button) findViewById(R.id.nbM);
        connect =(Button) findViewById(R.id.connect);
         rm =(Button) findViewById(R.id.runM);
        //deviceList = (ListView) findViewById(R.id.listView);
        final Button ballA =(Button)findViewById(R.id.ballA);
        Button ballM =(Button)findViewById(R.id.ballM);
        final Button wktA =(Button) findViewById(R.id.wktA);
        Button wktM =(Button) findViewById(R.id.wktM);
        //Button re =(Button) findViewById(R.id.re);
        Button legBA =(Button) findViewById(R.id.lbyesA);
        Button legBM =(Button) findViewById(R.id.lbyesM);
        Button legA =(Button) findViewById(R.id.byesA);
        Button legM =(Button) findViewById(R.id.byesM);

            dis =(Button) findViewById(R.id.disconnect);
        TextView teamOne = (TextView) findViewById(R.id.t1);
        TextView teamTwo = (TextView) findViewById(R.id.t2);
        conShow = (TextView) findViewById(R.id.conshow);
        conDis = (TextView) findViewById(R.id.condis);


         tB1 = (TextView) findViewById(R.id.tB1);
         tB2 = (TextView) findViewById(R.id.tB2);
         tshow = (TextView) findViewById(R.id.targetShow);
        wbShow= (TextView)findViewById(R.id.wbShow);
        final TextView run=(TextView)findViewById(R.id.runs);
        final TextView ex=(TextView) findViewById(R.id.extShow);
        final TextView nv=(TextView) findViewById(R.id.nbShow) ;
         ov =(TextView) findViewById(R.id.over);
         rr = (TextView) findViewById(R.id.rrate);
        //final TextView wkt=(TextView) findViewById(R.id.wkt);

        tB1.setVisibility(View.INVISIBLE);
        tB2.setVisibility(View.INVISIBLE);
        tshow.setVisibility(View.INVISIBLE);
        //conDis.setVisibility(View.INVISIBLE);
        dis.setVisibility(View.INVISIBLE);
       // conShow.setVisibility(View.INVISIBLE);

        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disconnect();
               // deviceList.setVisibility(View.VISIBLE);
                conShow.setVisibility(View.INVISIBLE);
                conDis.setVisibility(View.VISIBLE);
                dis.setVisibility(View.INVISIBLE);
                connect.setVisibility(View.VISIBLE);

            }
        });

//        re.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                send();
//            }
//        });


        teamOne.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                tB1.setVisibility(View.VISIBLE);
                tB2.setVisibility(View.INVISIBLE);
                r=0;
                w=0;
                ext=0;
                nb=0;
                overs=0;
                b=0;
                wik=0;
                tar = "0";
                target = 0;
                run.setText(String.valueOf(r)+"/"+wik);
                wbShow.setText(String.valueOf(w));
                ex.setText(String.valueOf(ext));
                nv.setText(String.valueOf(nb));
                ov.setText("("+overs+"."+b+")");
                Toast.makeText(getApplicationContext(),"Resend",Toast.LENGTH_LONG).show();
                send();

            }
        });



        teamTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tB1.setVisibility(View.INVISIBLE);
                tB2.setVisibility(View.VISIBLE);
                tshow.setVisibility(View.VISIBLE);
                target = r+1;
                flag =1;
                tar = String.valueOf(r+1);
                tshow.setText(tar);
                 r=0;
                 w=0;
                 ext=0;
                 nb=0;
                 overs=0;
                 b=0;
                 wik=0;
                run.setText(String.valueOf(r)+"/"+wik);
                wbShow.setText(String.valueOf(w));
                ex.setText(String.valueOf(ext));
                nv.setText(String.valueOf(nb));
                ov.setText("("+overs+"."+b+")");
                send();
            }
        });



        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect.setVisibility(View.INVISIBLE);
                dis.setVisibility(View.VISIBLE);
               // deviceList.setVisibility(View.VISIBLE);
                Connect();



            }
        });




        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r += 0;
                ball();
                run.setText(String.valueOf(r)+"/"+wik);
                send();


            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 r += 1;
                ball();
                 run.setText(String.valueOf(r)+"/"+wik);

                 send();

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r += 2;
                ball();
                run.setText(String.valueOf(r)+"/"+wik);

                send();


            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r += 3;
                ball();
                run.setText(String.valueOf(r)+"/"+wik);

                send();


            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r += 4;
                ball();
                run.setText(String.valueOf(r)+"/"+wik);

                send();


            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r += 6;
                ball();
                run.setText(String.valueOf(r)+"/"+wik);

                send();
            }
        });


        rm.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               if(r>0)
               {
                    r-=1;
                    run.setText(String.valueOf(r)+"/"+wik);

                    send();
               }
            }
        });
        wbA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r+=1;
                w+=1;
                ext+=1;
                run.setText(String.valueOf(r)+"/"+wik);
                wbShow.setText(String.valueOf(w));
                ex.setText(String.valueOf(ext));

                send();

            }
            });
        wbM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(w>0) {
                    r -= 1;
                    w -= 1;
                    ext -= 1;
                    run.setText(String.valueOf(r)+"/"+wik);
                    wbShow.setText(String.valueOf(w));
                    ex.setText(String.valueOf(ext));
                    send();
                }
            }
        });


        nbA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r+=1;
                ext+=1;
                nb+=1;
                if((b == 0) && (overs == 0))
                {
                    b=0;
                }

                else{

                    b-=1;
                }

                if( b == -1)
                {
                    if(overs>0)
                    {
                        overs -= 1;

                        b = 5;
                    }
                }




                ov.setText("("+overs+"."+b+")");
                run.setText(String.valueOf(r)+"/"+wik);
                //wbs.setText(String.valueOf(w));
                ex.setText(String.valueOf(ext));
                nv.setText(String.valueOf(nb));
                send();

            }
        });
        nbM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (nb>0) {
                   r -= 1;
                   ext -= 1;
                   nb -= 1;
                   run.setText(String.valueOf(r)+"/"+wik);
                   //wbs.setText(String.valueOf(w));
                   ex.setText(String.valueOf(ext));
                   nv.setText(String.valueOf(nb));
                   send();
               }


            }
        });
        ballA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b+=1;

                if (b == 6)
                {
                    overs += 1;
                    b=0;

                }


                ov.setText("("+overs+"."+b+")");
                send();


            }
        });

        ballM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

         if((b == 0) && (overs == 0))
         {
             b=0;
         }

          else{

                b-=1;
         }

                    if( b == -1)
                    {
                        if(overs>0)
                        {
                            overs -= 1;

                            b = 5;
                        }
                    }


                ov.setText("("+overs+"."+b+")");
                send();
            }
            });





        wktA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wik<10)
                {
                    wik += 1;
                    ball();
                }


              run.setText(String.valueOf(r)+"/"+String.valueOf(wik));
               // ov.setText("("+overs+"."+b+")");
                send();

            }
        });


        wktM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( wik>0)
                {
                    wik -= 1;
                    ballMinus();
                }
                run.setText(String.valueOf(r)+"/"+String.valueOf(wik));

                send();
            }
        });

        legBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r += 1;
                ball();
                run.setText(String.valueOf(r)+"/"+wik);

                send();

            }
        });
        legBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r>0){
                    r-=1;
                    run.setText(String.valueOf(r)+"/"+wik);
                    send();
                }
            }
        });
        legA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r += 1;
                ball();
                run.setText(String.valueOf(r)+"/"+wik);

                send();

            }
        });
        legM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r>0){
                    r-=1;
                    run.setText(String.valueOf(r)+"/"+wik);
                    send();
                }
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.con ) {
            Connect();
        }else if(id == R.id.dcon) {
            Disconnect();
        }else if(id == R.id.setting) {
             Setting();
        }

        return super.onOptionsItemSelected(item);

    }

    private void Setting() {
    }


    private void send() {
        if(isBtConnected){
        String  data ="0";
        if((r >= target) && (flag == 1)){

            data = r +"/" + wik+"," + overs+"." + b+ "$" +"won"+"#";
            flag = 0;

        }else
        {
             data = r +"/" + wik+"," + overs+"." + b+ "$" + tar+"#";

        }

            byte[] bytes = data.getBytes();           //converts entered String into bytes
            try {
                btSocket.getOutputStream().write(bytes);

            } catch (IOException e) { }
        }
    }

    private void  Connect(){

        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        if(myBluetooth == null){
            Toast.makeText(getApplicationContext(),"Bluetooth is not available",Toast.LENGTH_LONG).show();
        }else{
            if(!myBluetooth.isEnabled()){
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnBTon,1);
            }
        }

        pairedDeviceList();


    }

    private class ConnectBT extends AsyncTask<Void,Void,Void>{

        private boolean ConnectSuccess = true;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                if (btSocket == null || !isBtConnected) {
                    //get the mobile bluetooth device
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();

                    //connects to the device's address and checks if it's available
                         BluetoothDevice device = myBluetooth.getRemoteDevice(address);

                        btSocket = device.createInsecureRfcommSocketToServiceRecord(myUUID);

                        //create a RFCOMM (SPP) connection
                        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();


                        //start connection

                        btSocket.connect();

                }
            } catch (IOException e) {
                //if the connection try failed, you can check the exception here.
                ConnectSuccess = false;
                isBtConnected = false;

                Toast.makeText(getApplicationContext(),"Turn on the Scoreboard",Toast.LENGTH_LONG).show();

            }
            return null;
        }

        protected void onPostExecute(Void result){

            if (!ConnectSuccess) {

                    msg("Connection failed. Try again.");
                    finish();
            }
            else
            {

                msg("Connected.");
                isBtConnected = true;
               // deviceList.setVisibility(View.INVISIBLE);
                 conDis.setVisibility(View.INVISIBLE);
                 conShow.setVisibility(View.VISIBLE);

               // conShow.setText("Connected");

            }
        }
    }
    private void msg(String s) {

        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    private void Disconnect() {
        if (btSocket != null) //if the btSocket is busy
        {
            try {
                btSocket.close(); //close connection
                isBtConnected = false;
                msg("Disconnected");

            } catch (IOException e) {
                msg("Error");
            }
        }
        //finish(); //return to the first layout
    }

    private void pairedDeviceList() {

        Set<BluetoothDevice> pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();
        if(pairedDevices.size()>0){
            for(BluetoothDevice bt : pairedDevices){
                if(bt.getName().equalsIgnoreCase("SCOREBOARD")) {
                   // list.add(bt.getName());
                    address = bt.getAddress();
                    new ConnectBT().execute();

                }
            }
        }else {
            Toast.makeText(getApplicationContext(),"No Scoreboard found",Toast.LENGTH_LONG).show();
            finish();
        }

//        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
//        deviceList.setAdapter(adapter);
//        deviceList.setOnItemClickListener(myListClickListner);
    }
//    private AdapterView.OnItemClickListener myListClickListner = new AdapterView.OnItemClickListener() {
//         @Override
//             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//             String info = ((TextView) view).getText().toString();
//            // address = info.substring(info.length()-17);
//             //new ConnectBT().execute();
//         }
//     };
    private void ball() {
        b+=1;

        if (b == 6)
        {
            overs += 1;
            b=0;

        }


        ov.setText(overs+"."+b);

    }

    private  void won(){

        if( r == target ){
             tar = " ! WON !";
        }
    }

    private void ballMinus(){
        if((b == 0) && (overs == 0))
        {
            b=0;
        }

        else{

            b-=1;
        }

        if( b == -1)
        {
            if(overs>0)
            {
                overs -= 1;

                b = 5;
            }
        }


        ov.setText("("+overs+"."+b+")");
    }

}

