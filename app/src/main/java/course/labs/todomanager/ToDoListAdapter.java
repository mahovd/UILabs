package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Create a View for the ToDoItem at specified position
	// Remember to check whether convertView holds an already allocated View
	// before created a new View.
	// Consider using the ViewHolder pattern to make scrolling more efficient
	// See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// TODO - Get the current ToDoItem
		final ToDoItem toDoItem = (ToDoItem) getItem(position);

		// TODO - Inflate the View for this ToDoItem
		// from todo_item.xml
		RelativeLayout itemLayout = (RelativeLayout) convertView;

        if (itemLayout == null) {
            itemLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.todo_item,null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.titleView = (TextView) itemLayout.findViewById(R.id.titleView);
            viewHolder.statusCheckBox = (CheckBox) itemLayout.findViewById(R.id.statusCheckBox);
            viewHolder.priorityView = (TextView) itemLayout.findViewById(R.id.priorityView);
            viewHolder.dateView = (TextView) itemLayout.findViewById(R.id.dateView);
            itemLayout.setTag(viewHolder);
        }

		// TODO - Fill in specific ToDoItem data
		// Remember that the data that goes in this View
		// corresponds to the user interface elements defined
		// in the layout file
        final ViewHolder holder = (ViewHolder) itemLayout.getTag();



		// TODO - Display Title in TextView
		//final TextView titleView = null;
        holder.titleView.setText(toDoItem.getTitle());

		// TODO - Set up Status CheckBox
        if (toDoItem.getStatus()== ToDoItem.Status.DONE) {
            holder.statusCheckBox.setChecked(true);
        }
        else {

            holder.statusCheckBox.setChecked(false);
        }

		final CheckBox statusView = holder.statusCheckBox;

		statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Log.i(TAG, "Entered onCheckedChanged()");

				// TODO - set up an OnCheckedChangeListener, which
				// is called when the user toggles the status checkbox
                if (isChecked==true){
                    toDoItem.setStatus(ToDoItem.Status.DONE);
                    holder.statusCheckBox.setChecked(true);
                }
                else{
                    toDoItem.setStatus(ToDoItem.Status.NOTDONE);
                    holder.statusCheckBox.setChecked(false);
                }

			}
		});

		// TODO - Display Priority in a TextView

		//final TextView priorityView = null;
        holder.priorityView.setText(toDoItem.getPriority().toString());

		// TODO - Display Time and Date.
		// Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and
		// time String

		//final TextView dateView = null;
        holder.dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));

		// Return the View you just created
		return itemLayout;

	}

    static class ViewHolder{
        TextView titleView;
        CheckBox statusCheckBox;
        TextView priorityView;
        TextView dateView;

    }
}
