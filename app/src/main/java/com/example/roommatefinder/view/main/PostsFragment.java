package com.example.roommatefinder.view.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.roommatefinder.R;

import com.example.roommatefinder.model.Posting;
import com.example.roommatefinder.model.service.request.PostingsRequest;
import com.example.roommatefinder.model.service.response.PostingsResponse;
import com.example.roommatefinder.net.asynctasks.PostingsTaskFacade;
import com.example.roommatefinder.presenter.PostingsPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PostsFragment extends Fragment implements PostingsPresenter.View {

    private static final String LOG_TAG = "PostsFragment";

    private static final int LOADING_DATA_VIEW = 0;
    private static final int ITEM_VIEW = 1;

    private static final int PAGE_SIZE = 10;

    private PostingsRecyclerViewAdapter postingsRecyclerViewAdapter;

    private PostingsPresenter presenter;

    @Override
    public void onPostingsResult(PostingsResponse response) {
        //TODO: IMPLEMENT LOGIC FOR THE FUNCTION.
    }

    /**
     * Creates an instance of the fragment.
     *
     */
    public static PostsFragment newInstance() {
        PostsFragment fragment = new PostsFragment();

//        Bundle args = new Bundle(2);
//        args.putSerializable(USER_KEY, user);
//        args.putSerializable(AUTH_TOKEN_KEY, authToken);

        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        //noinspection ConstantConditions
//        user = (User) getArguments().getSerializable(USER_KEY);
//        authToken = (AuthToken) getArguments().getSerializable(AUTH_TOKEN_KEY);

        presenter = new PostingsPresenter(this);

        RecyclerView postingsRecyclerView = view.findViewById(R.id.postsRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        postingsRecyclerView.setLayoutManager(layoutManager);

        postingsRecyclerViewAdapter = new PostsFragment.PostingsRecyclerViewAdapter();
        postingsRecyclerView.setAdapter(postingsRecyclerViewAdapter);

        postingsRecyclerView.addOnScrollListener(new PostsFragment.PostingsRecyclerViewPaginationScrollListener(layoutManager));

        return view;
    }

    /**
     * The ViewHolder for the RecyclerView that displays the Feed data.
     */
    private class PostingsHolder extends RecyclerView.ViewHolder {

        private final TextView postFullName;
        private final TextView postContent;

        /**
         * Creates an instance and sets an OnClickListener for the tweet's row.
         *
         * @param itemView the view on which the user will be displayed.
         */

        PostingsHolder(@NonNull View itemView) {
            super(itemView);

            postFullName = itemView.findViewById(R.id.post_full_name);
            postContent = itemView.findViewById(R.id.post_content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getContext(), "You selected '" + userName.getText() + "'.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        /**
         * Binds the user's data to the view.
         *
         * @param posting the posting.
         */
        void bindPosting(Posting posting) {

            postFullName.setText(posting.getUser().getFirstName() + " " + posting.getUser().getLastName());

            String country = posting.getCountry();
            String city = posting.getCity();
            String street = posting.getStreet();
            String state = posting.getState();
            Integer buildNum = posting.getBuildNum();
            Integer roomNum = posting.getRoomNum();

            postContent.setText("" + buildNum + street + ", " + city + " " + state);
        }


    }


    /**
     * The adapter for the RecyclerView that displays the Followers data.
     */
    private class PostingsRecyclerViewAdapter extends RecyclerView.Adapter<PostsFragment.PostingsHolder> implements PostingsTaskFacade.Observer {

        private final List<Posting> postings = new ArrayList<>();

        private Posting lastPosting;

        private boolean hasMorePages;
        private boolean isLoading = false;

        /**
         * Creates an instance and loads the first page of following data.
         */
        PostingsRecyclerViewAdapter() {
            loadMoreItems();
        }

        /**
         * Adds new tweets to the list from which the RecyclerView retrieves the tweets it displays
         * and notifies the RecyclerView that items have been added.
         *
         * @param newTweets the tweets to add.
         */
        void addItems(List<Posting> newTweets) {
            int startInsertPosition = postings.size();
            postings.addAll(newTweets);
            this.notifyItemRangeInserted(startInsertPosition, newTweets.size());
        }

        /**
         * Adds a single user to the list from which the RecyclerView retrieves the tweets it
         * displays and notifies the RecyclerView that an item has been added.
         *
         * @param posting the posting to add.
         */
        void addItem(Posting posting) {
            postings.add(posting);
            this.notifyItemInserted(postings.size() - 1);
        }

        /**
         * Removes a user from the list from which the RecyclerView retrieves the tweets it displays
         * and notifies the RecyclerView that an item has been removed.
         *
         * @param posting the user to remove.
         */
        void removeItem(Posting posting) {
            int position = postings.indexOf(posting);
            postings.remove(position);
            this.notifyItemRemoved(position);
        }

        /**
         *  Creates a view holder for a tweet to be displayed in the RecyclerView or for a message
         *  indicating that new rows are being loaded if we are waiting for rows to load.
         *
         * @param parent the parent view.
         * @param viewType the type of the view (ignored in the current implementation).
         * @return the view holder.
         */
        @NonNull
        @Override
        public PostsFragment.PostingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(PostsFragment.this.getContext());
            View view;

            if(viewType == LOADING_DATA_VIEW) {
                view =layoutInflater.inflate(R.layout.post_row, parent, false);

            } else {
                view = layoutInflater.inflate(R.layout.post_row, parent, false);
            }

            return new PostsFragment.PostingsHolder(view);
        }

        /**
         * Binds the tweet at the specified position unless we are currently loading new data. If
         * we are loading new data, the display at that position will be the data loading footer.
         *
         * @param postingsHolder the ViewHolder to which the tweet should be bound.
         * @param position the position (in the list of followers) that contains the follower to be
         *                 bound.
         */
        @Override
        public void onBindViewHolder(@NonNull PostingsHolder postingsHolder, int position) {
            if(!isLoading) {
                postingsHolder.bindPosting(postings.get(position));
            }
        }

        /**
         * Returns the current number of tweets available for display.
         * @return the number of tweets available for display.
         */
        @Override
        public int getItemCount() {
            return postings.size();
        }

        /**
         * Returns the type of the view that should be displayed for the item currently at the
         * specified position.
         *
         * @param position the position of the items whose view type is to be returned.
         * @return the view type.
         */
        @Override
        public int getItemViewType(int position) {
            return (position == postings.size() - 1 && isLoading) ? LOADING_DATA_VIEW : ITEM_VIEW;
        }

        /**
         * Causes the Adapter to display a loading footer and make a request to get more followers
         * data.
         */
        void loadMoreItems() {
            isLoading = true;
            addLoadingFooter();

            PostingsTaskFacade postingsTaskFacade = new PostingsTaskFacade();
            PostingsRequest request = new PostingsRequest(10, lastPosting);
            postingsTaskFacade.execute(request);
        }

        /**
         * A callback indicating more followers data has been received. Loads the new followees
         * and removes the loading footer.
         *
         * @param postingsResponse the asynchronous response to the request to load more items.
         */
        @Override
        public void onPostingsResponseReceived(PostingsResponse postingsResponse) {
            List<Posting> postings = postingsResponse.getPostings();

            lastPosting = (postings.size() > 0) ? postings.get(postings.size() -1) : null;
            hasMorePages = postingsResponse.getHasMorePages();

            isLoading = false;
            removeLoadingFooter();
            postingsRecyclerViewAdapter.addItems(postings);
        }

        /**
         * A callback indicating that an exception was thrown by the presenter.
         *
         * @param exception the exception.
         */
//        @Override
//        public void handleException(Exception exception) {
//            Log.e(LOG_TAG, exception.getMessage(), exception);
//            removeLoadingFooter();
//            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
//        }

        /**
         * Adds a dummy user to the list of users so the RecyclerView will display a view (the
         * loading footer view) at the bottom of the list.
         */
        private void addLoadingFooter() {

            addItem(new Posting(null,"","", "", "", 0, 0));
        }

        /**
         * Removes the dummy user from the list of users so the RecyclerView will stop displaying
         * the loading footer at the bottom of the list.
         */
        private void removeLoadingFooter() {
            removeItem(postings.get(postings.size() - 1));
        }
    }


    /**
     * A scroll listener that detects when the user has scrolled to the bottom of the currently
     * available data.
     */
    private class PostingsRecyclerViewPaginationScrollListener extends RecyclerView.OnScrollListener {

        private final LinearLayoutManager layoutManager;

        /**
         * Creates a new instance.
         *
         * @param layoutManager the layout manager being used by the RecyclerView.
         */
        PostingsRecyclerViewPaginationScrollListener(LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        /**
         * Determines whether the user has scrolled to the bottom of the currently available data
         * in the RecyclerView and asks the adapter to load more data if the last load request
         * indicated that there was more data to load.
         *
         * @param recyclerView the RecyclerView.
         * @param dx the amount of horizontal scroll.
         * @param dy the amount of vertical scroll.
         */
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!postingsRecyclerViewAdapter.isLoading && postingsRecyclerViewAdapter.hasMorePages) {
                if ((visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && firstVisibleItemPosition >= 0) {
                    postingsRecyclerViewAdapter.loadMoreItems();
                }
            }
        }
    }
}
