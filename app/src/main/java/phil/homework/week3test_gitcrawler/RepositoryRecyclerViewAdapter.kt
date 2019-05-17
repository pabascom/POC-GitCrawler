package phil.homework.week3test_gitcrawler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_item_repository.view.*


import phil.homework.week3test_gitcrawler.RepositoryRecyclerViewFragment.OnListFragmentInteractionListener

import phil.homework.week3test_gitcrawler.model.repo.Repository
import kotlin.random.Random

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class RepositoryRecyclerViewAdapter(
    private val values: List<Repository>,
    private val listener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Repository
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            listener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.ownerName.text = item.owner.login
        holder.repoName.text = item.name
        holder.numCommits.text = Random.nextInt(30).toString() // github api doesn't give you commit counts: https://stackoverflow.com/questions/27931139/how-to-use-github-v3-api-to-get-commit-count-for-a-repo

        with(holder.view) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ownerName: TextView = view.tvOwnerName
        val repoName: TextView = view.tvRepoName
        val numCommits: TextView = view.tvNumCommits

        override fun toString(): String {
            return super.toString() + " ${ownerName.text}/${repoName.text}"
        }
    }
}
