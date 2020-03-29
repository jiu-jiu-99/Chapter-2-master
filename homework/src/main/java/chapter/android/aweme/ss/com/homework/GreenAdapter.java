package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

/**
 * 适配器
 */
public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    private static final String TAG = "GreenAdapter";

    private int mNumberItems;

    private List<Message> mdata;

    private final ListItemClickListener mOnClickListener;

    private static int viewHolderCount;

    public GreenAdapter(int numListItems, ListItemClickListener listener,List<Message> messages) {
        mNumberItems = numListItems;
        mOnClickListener = listener;
        mdata = messages;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        Message message = mdata.get(position);
        numberViewHolder.bind(message);

    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Message message;
        private boolean isOfficial;//判断有没有官方
        private TextView title;//名称
        private TextView time;//时间
        private TextView description;//描述
        private CircleImageView avatar;//头像
        private ImageView robot_notice;//官方

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);//名称
            time = itemView.findViewById(R.id.tv_time);//时间
            description = itemView.findViewById(R.id.tv_description);//描述
            avatar = itemView.findViewById(R.id.iv_avatar);//头像
            robot_notice = itemView.findViewById(R.id.robot_notice);//官方
            itemView.setOnClickListener(this);
        }

        public void bind(Message message) {
            title.setText(message.getTitle());
            time.setText(message.getTime());
            description.setText(message.getDescription());
            if (message.isOfficial()){
                robot_notice.setVisibility(View.VISIBLE);//官方设为可见
            }

            switch (message.getIcon()){
                case "TYPE_ROBOT":
                    avatar.setImageResource(R.drawable.session_robot);
                    break;
                case "TYPE_GAME":
                    avatar.setImageResource(R.drawable.icon_micro_game_comment);
                    break;
                case "TYPE_SYSTEM":
                    avatar.setImageResource(R.drawable.session_system_notice);
                    break;
                case "TYPE_STRANGER":
                    avatar.setImageResource(R.drawable.session_stranger);
                    break;
                case "TYPE_USER":
                    avatar.setImageResource(R.drawable.icon_girl);
                    break;
                default:
                    break;
            }

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}

