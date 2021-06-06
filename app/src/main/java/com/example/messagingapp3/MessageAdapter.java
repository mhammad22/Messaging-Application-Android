package com.example.messagingapp3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    //View Holder Number 1
    public class MessageViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView txt;
        private TextView tme;
        private TextView Nme;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.ImgPerson);
            txt = (TextView) itemView.findViewById(R.id.TextPerson);
            tme = (TextView) itemView.findViewById(R.id.text_message_time_p);
            Nme= (TextView)itemView.findViewById(R.id.text_message_nameP);

        }

        public TextView getLabel1() {
            return txt;
        }

        public void setLabel1(TextView label1) {
            this.txt = label1;
        }


        public TextView getLabel3() {
            return tme;
        }

        public void setLabel3(TextView label3) {
            this.tme = label3;
        }

        public ImageView getLabel2() {
            return img;
        }

        public void setLabel2(ImageView label2) {
            this.img = label2;
        }

        public TextView getLabel4() {
            return Nme;
        }

        public void setLabel4(TextView label4) {
            this.Nme = label4;
        }


        private void configureViewHolder2(MessageViewHolder vh2, int position) {

            String TextMessage = (String) msg.get(position).getMessage();
            String Time = msg.get(position).getTime();
            String PersonName=msg.get(position).getName();

            if (TextMessage != null) {
                vh2.getLabel1().setText(TextMessage);
                vh2.getLabel3().setText(Time);
                vh2.getLabel4().setText(PersonName);
            }
        }

    }

    //View Holder Number 2
    public class MeViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView txt;
        private TextView tm;
        private TextView Nm;

        public MeViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.Imgme);
            txt = (TextView) itemView.findViewById(R.id.Textme);
            tm = (TextView) itemView.findViewById(R.id.text_message_time_me);
            Nm = (TextView) itemView.findViewById(R.id.text_message_nameM);
        }

        public TextView getLabel1() {
            return txt;
        }

        public void setLabel1(TextView label1) {
            this.txt = label1;
        }

        public ImageView getLabel2() {
            return img;
        }

        public void setLabel2(ImageView label2) {
            this.img = label2;
        }

        public TextView getLabel3() {
            return tm;
        }

        public void setLabel3(TextView label3) {
            this.tm = label3;
        }

        public TextView getLabel4() {
            return Nm;
        }

        public void setLabel4(TextView label4) {
            this.Nm = label4;
        }

        private void configureViewHolder1(MeViewHolder vh1, int position) {
            String TextMessage = (String) msg.get(position).getMessage();
            String Time = msg.get(position).getTime();
            String PersonName=msg.get(position).getName();

            if (TextMessage != null) {
                vh1.getLabel1().setText(TextMessage);
                vh1.getLabel3().setText(Time);
                vh1.getLabel4().setText(PersonName);
            }
        }

    }


    List<ChatMessage> msg = new ArrayList<>();


    public MessageAdapter(List<ChatMessage> obj) {
        this.msg = obj;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(msg.get(position).getType().equals("Send")) {
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewgroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewgroup.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.layout_me, viewgroup, false);
                viewHolder = new MeViewHolder(v1);
                break;

            case 1:
                View v2 = inflater.inflate(R.layout.layout_person, viewgroup, false);
                viewHolder = new MessageViewHolder(v2);
                break;

            default:
                viewHolder = new MeViewHolder(inflater.inflate(R.layout.layout_me, viewgroup, false));
                break;
        }
        return viewHolder;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case 0:
                MeViewHolder vh1 = (MeViewHolder) viewHolder;
                vh1.configureViewHolder1(vh1, position);
                break;
            case 1:
                MessageViewHolder vh2 = (MessageViewHolder) viewHolder;
                vh2.configureViewHolder2(vh2, position);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return msg.size();
    }


}
